package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(
      RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/show/{id}")
  public String showById(@PathVariable String id, Model model) {

    model.addAttribute("recipe", recipeService.findById(new Long(id)));

    return "recipe/show";
  }

  @RequestMapping("recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());

    return "recipe/recipeform";
  }

  //Model Attribute ile form post parametrelerine command object'ine bağlıyoruz.
  @PostMapping
  @RequestMapping("recipe")
  public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
    return "redirect:/recipe/show/" + savedCommand.getId();
  }

  @RequestMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return "recipe/recipeform";
  }

  @GetMapping
  @RequestMapping("recipe/{id}/delete")
  public String deleteById(@PathVariable String id) {

    log.debug("Deleting id: " + id);

    recipeService.deleteById(Long.valueOf(id));

    return "redirect:/";
  }

}
