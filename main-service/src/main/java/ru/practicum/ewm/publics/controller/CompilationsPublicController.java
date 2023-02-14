package ru.practicum.ewm.publics.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.publics.service.compilations.CompilationsPublicService;

import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(value = "/compilations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class CompilationsPublicController {
    private final CompilationsPublicService service;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(name = "pinned", defaultValue = "false", required = false) String pinnedSt,
                                                @RequestParam(name = "from", defaultValue = "0", required = false) @Min(0) Integer from,
                                                @RequestParam(name = "size", defaultValue = "10", required = false) @Min(1) Integer size) {
        Boolean pinned = Optional.of(Boolean.parseBoolean(pinnedSt))
                .orElseThrow(() -> new ValidationException("Field: pinned. Error: must not be blank. Value: true/false"));
        return service.getCompilations(pinned, from,size);
    }

    @GetMapping("/{compId}")
    public ResponseEntity<CompilationDto> getCompilation(@PathVariable Long compId) {
        return ResponseEntity.ok(service.getCompilation(compId));
    }
}
