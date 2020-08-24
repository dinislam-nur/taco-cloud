package com.first.springboot.demo.Web;

import com.first.springboot.demo.domains.Ingredient;
import com.first.springboot.demo.domains.Order;
import com.first.springboot.demo.domains.Taco;
import com.first.springboot.demo.data.ingredients.IngredientRepository;
import com.first.springboot.demo.data.tacos.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.first.springboot.demo.domains.Ingredient.Type;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository designRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.designRepository = tacoRepository;
    }

    @ModelAttribute
    public void addIngredientsAttribute(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "design")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }


    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, @ModelAttribute("order") Order order){

        if(errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepository.save(design);
        order.addDesign(saved);

        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
       return ingredients.stream()
                         .filter(ingredient -> type.equals(ingredient.getType()))
                         .collect(toList());
    }
}
