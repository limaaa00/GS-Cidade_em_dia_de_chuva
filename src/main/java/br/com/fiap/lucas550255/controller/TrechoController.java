package br.com.fiap.lucas550255.controller;

import br.com.fiap.lucas550255.dto.TrechoDTO;
import br.com.fiap.lucas550255.dto.ValoresPermitidos;
import br.com.fiap.lucas550255.exception.RegraNegocioException;
import br.com.fiap.lucas550255.service.BairroService;
import br.com.fiap.lucas550255.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trechos")
public class TrechoController {

    private static final String FORM = "trechos/form";
    private static final String REDIRECT_LISTA = "redirect:/trechos";

    private final TrechoService trechoService;
    private final BairroService bairroService;

    public TrechoController(TrechoService trechoService, BairroService bairroService) {
        this.trechoService = trechoService;
        this.bairroService = bairroService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        carregarListas(model);
        return FORM;
    }

    @PostMapping
    public String incluir(@Valid @ModelAttribute("trecho") TrechoDTO dto,
                          BindingResult result, Model model, RedirectAttributes ra) {
        carregarListas(model);
        if (result.hasErrors()) {
            return FORM;
        }
        try {
            trechoService.incluir(dto);
            ra.addFlashAttribute("sucesso", "Trecho incluído com sucesso.");
            return REDIRECT_LISTA;
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("trecho", trechoService.buscar(id));
            carregarListas(model);
            return FORM;
        } catch (RegraNegocioException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return REDIRECT_LISTA;
        }
    }

    @PostMapping("/{id}")
    public String alterar(@PathVariable Long id,
                          @Valid @ModelAttribute("trecho") TrechoDTO dto,
                          BindingResult result, Model model, RedirectAttributes ra) {
        dto.setId(id);
        carregarListas(model);
        if (result.hasErrors()) {
            return FORM;
        }
        try {
            trechoService.alterar(id, dto);
            ra.addFlashAttribute("sucesso", "Trecho alterado com sucesso.");
            return REDIRECT_LISTA;
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM;
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            trechoService.excluir(id);
            ra.addFlashAttribute("sucesso", "Trecho excluído com sucesso.");
        } catch (RegraNegocioException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return REDIRECT_LISTA;
    }

    private void carregarListas(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        model.addAttribute("tipos", ValoresPermitidos.TIPOS_CALCADA);
    }
}