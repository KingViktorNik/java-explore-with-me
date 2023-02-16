package ru.practicum.ewm.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.admin.service.compilations.CompilationsAdminService;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.CompilationNewDto;
import ru.practicum.ewm.dto.compilation.CompilationUpdateDto;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping(value = "/admin/compilations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class CompilationsAdminController {
    private final CompilationsAdminService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompilationDto> addCompilation(@Valid @RequestBody CompilationNewDto compilationNewDto) {
        CompilationDto result = service.addCompilation(compilationNewDto);

        return ResponseEntity.status(201).body(result);

    }

    @PatchMapping(path = "/{compId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompilationDto> updateCompilation(@PathVariable Long compId,
                                                            @Valid @RequestBody CompilationUpdateDto compilationUpdateDto) {
        CompilationDto result = service.updateCompilation(compId, compilationUpdateDto);

        return ResponseEntity.status(200).body(result);

    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable Long compId) {
        service.deleteCompilation(compId);

        return ResponseEntity.status(204).build();

    }

}
