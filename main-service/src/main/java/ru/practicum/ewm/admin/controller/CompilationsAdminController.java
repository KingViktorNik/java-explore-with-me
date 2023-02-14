package ru.practicum.ewm.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.admin.service.compilations.CompilationsAdminService;
import ru.practicum.ewm.dto.compilation.CompilationDto;
import ru.practicum.ewm.dto.compilation.CompilationNewDto;
import ru.practicum.ewm.dto.compilation.CompilationUpdateDto;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/admin/compilations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
@Validated
public class CompilationsAdminController {
    private final CompilationsAdminService service;

    @PostMapping//(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompilationDto> addCompilation(@RequestBody @Valid CompilationNewDto compilationNewDto) {
        return ResponseEntity.status(201).body(service.addCompilation(compilationNewDto));
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<CompilationDto> updateCompilation(@PathVariable Long compId,
                                                            @RequestBody CompilationUpdateDto compilationUpdateDto) {
        return ResponseEntity.ok(service.updateCompilation(compId, compilationUpdateDto));
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable Long compId) {
        service.deleteCompilation(compId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
