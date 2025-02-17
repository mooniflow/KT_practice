package com.hello.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hello.demo.entity.CodeDetail;
import com.hello.demo.entity.CodeGroup;
import com.hello.demo.service.CodeService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8082")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping("/code-groups")
    public ResponseEntity<List<CodeGroup>> getCodeGroups() {
        return ResponseEntity.ok(codeService.getAllCodeGroups());
    }

    @PostMapping("/code-groups")
    public ResponseEntity<CodeGroup> createCodeGroup(@RequestBody CodeGroup codeGroup) {
        return ResponseEntity.ok(codeService.saveCodeGroup(codeGroup));
    }

    @DeleteMapping("/code-groups/{id}")
    public ResponseEntity<Void> deleteCodeGroup(@PathVariable String id) {
        codeService.deleteCodeGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/code-details")
    public ResponseEntity<List<CodeDetail>> getCodeDetails(@RequestParam("groupId") String groupId) {
        return ResponseEntity.ok(codeService.getCodeDetails(groupId));
    }

    @PostMapping("/code-details")
    public ResponseEntity<CodeDetail> createCodeDetail(@RequestBody CodeDetail codeDetail) {
        return ResponseEntity.ok(codeService.saveCodeDetail(codeDetail));
    }

    @DeleteMapping("/code-details/{id}")
    public ResponseEntity<Void> deleteCodeDetail(@PathVariable String id) {
        codeService.deleteCodeDetail(id);
        return ResponseEntity.noContent().build();
    }
}
