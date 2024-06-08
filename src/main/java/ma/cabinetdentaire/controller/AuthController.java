package ma.cabinetdentaire.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cabinetdentaire.entity.Utilisateur;
import ma.cabinetdentaire.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Data @NoArgsConstructor @AllArgsConstructor
@Controller
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public String getLogin(Model model){
        Utilisateur user = new Utilisateur();
        model.addAttribute("user", user);
        return "login";
    }
    @PostMapping("/auth")
    public String login(@ModelAttribute("user") Utilisateur user){
        authService.login(user.getNomUtilisateur(),user.getMotDePasse());
        return "redirect:/patients";
    }
}
