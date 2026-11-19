package br.com.fiap.lucas550255.controller;

import br.com.fiap.lucas550255.dto.BairroDTO;
import br.com.fiap.lucas550255.dto.ValoresPermitidos;
import br.com.fiap.lucas550255.exception.RegraNegocioException;
import br.com.fiap.lucas550255.service.BairroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bairros")
public class BairroController {

    private static final String FORM = "bairros/form";
    private static final String REDIRECT_LISTA = "redirect:/bairros";

    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        model.addAttribute("zonas", ValoresPermitidos.ZONAS);
        return FORM;
    }

    @PostMapping
    public String incluir(@Valid @ModelAttribute("bairro") BairroDTO dto,
                          BindingResult result, Model model, RedirectAttributes ra) {
        model.addAttribute("zonas", ValoresPermitidos.ZONAS);
        if (result.hasErrors()) {
            return FORM;
        }
        try {
            bairroService.incluir(dto);
            ra.addFlashAttribute("sucesso", "Bairro incluído com sucesso.");
            return REDIRECT_LISTA;
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM;
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("bairro", bairroService.buscar(id));
            model.addAttribute("zonas", ValoresPermitidos.ZONAS);
            return FORM;
        } catch (RegraNegocioException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            return REDIRECT_LISTA;
        }
    }

    @PostMapping("/{id}")
    public String alterar(@PathVariable Long id,
                          @Valid @ModelAttribute("bairro") BairroDTO dto,
                          BindingResult result, Model model, RedirectAttributes ra) {
        dto.setId(id);
        model.addAttribute("zonas", ValoresPermitidos.ZONAS);
        if (result.hasErrors()) {
            return FORM;
        }
        try {
            bairroService.alterar(id, dto);
            ra.addFlashAttribute("sucesso", "Bairro alterado com sucesso.");
            return REDIRECT_LISTA;
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            return FORM;
        }
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            bairroService.excluir(id);
            ra.addFlashAttribute("sucesso", "Bairro excluído com sucesso.");
        } catch (RegraNegocioException e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return REDIRECT_LISTA;
    }
}