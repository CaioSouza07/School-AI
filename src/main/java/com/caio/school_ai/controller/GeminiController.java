package com.caio.school_ai.controller;

import com.caio.school_ai.model.service.GeminiService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService){
        this.geminiService = geminiService;
    }

    @GetMapping("/pergunta")
    public String perguntar(@RequestParam String p) throws Exception{
        return geminiService.gerarResposta(p);
    }

    @PostMapping(value = "/resumo-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String gerarResumoPDF(@RequestParam("file") MultipartFile file) throws Exception{

        byte[] pdfBytes = file.getBytes();

        return geminiService.gerarResumoPDF(pdfBytes);

    }

    @PostMapping(value = "/mapa-mental-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String gerarMapaMentalPDF(@RequestParam("file") MultipartFile file) throws Exception{

        byte[] pdfBytes = file.getBytes();

        return geminiService.gerarMapaMentalPDF(pdfBytes);

    }

    @PostMapping(value = "/questoes-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String gerarQuestoesPDF(@RequestParam("file") MultipartFile file) throws Exception{

        byte[] pdfBytes = file.getBytes();

        return geminiService.gerarQuestoesPDF(pdfBytes);

    }

}
