package br.com.fiap.lucas550255.controller;

import br.com.fiap.lucas550255.dto.LeituraDTO;
import br.com.fiap.lucas550255.exception.RegraNegocioException;
import br.com.fiap.lucas550255.service.LeituraService;
import br.com.fiap.lucas550255.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/leituras")
public class LeituraController {

    private static final String FORM = "leituras/form";
    private static final String REDIRECT_LISTA = "redirect:/leituras";

    private final LeituraService leituraService;
    private final TrechoService trechoService;

    public LeituraController(LeituraService leituraService, TrechoService trechoService) {
        this.leituraService = leituraService;
        this.trechoService = trechoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", leituraService.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        model.addAttribute("trechos", trechoService.listar());
        return FORM;
    }

    @PostMapping
    public String incluir(@Valid @ModelAttribute("leitura") LeituraDTO dto,
                          BindingResult result, Model model, RedirectAttributes ra) {
        model.addAttribute("trechos", trechoService.listar());
        if (result.hasErrors()) {
            return FORM;
        }
        try {
            leituraService.incluir(dto);
            ra.addFlashAttribute("sucesso", "Leitura incluída com sucesso.");
            return REDIRECT_LISTA;
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("leitura", leituraService.buscar(id));
            model.addAttribute("trechos", trechoService.listar());
            return FORM;
        } catch (RegraNegocioException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return REDIRECT_LISTA;
        }
    }

    @PostMapping("/{id}")
    public String alterar(@PathVariable Long id,
                          @Valid @ModelAttribute("leitura") LeituraDTO dto,
                          BindingResult result, Model model, RedirectAttributes ra) {
        dto.setId(id);
        model.addAttribute("trechos", trechoService.listar());
        if (result.hasErrors()) {
            return FORM;
        }
        try {
            leituraService.alterar(id, dto);
            ra.addFlashAttribute("sucesso", "Leitura alterada com sucesso.");
            return REDIRECT_LISTA;
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM;
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            leituraService.excluir(id);
            ra.addFlashAttribute("sucesso", "Leitura excluída com sucesso.");
        } catch (RegraNegocioException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return REDIRECT_LISTA;
    }
}