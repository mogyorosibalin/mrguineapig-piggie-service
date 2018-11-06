package com.codecool.mrguinepig.piggieservice.controller;

import com.codecool.mrguinepig.piggieservice.model.GuineaPig;
import com.codecool.mrguinepig.piggieservice.repository.GuineaPigRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST })
@RestController
public class NameController {

    @Autowired
    private GuineaPigRepository guineaPigRepository;

    @ModelAttribute("GuineaPig")
    public GuineaPig getGuineaPig() {
        return new GuineaPig();
    }

    @GetMapping("/get-name")
    public String getName(@ModelAttribute GuineaPig guineaPig) {
        JSONObject json = new JSONObject();
        GuineaPig gPig = guineaPigRepository.findTopByIdIsNotNullOrderByIdDesc().orElse(guineaPig);
        json.put("name", gPig.getName());
        return json.toString();
    }

    @PostMapping("/save-name")
    public String saveName(@ModelAttribute GuineaPig guineaPig, @RequestBody String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject request = (JSONObject) parser.parse(body);
        guineaPig.setName((String) request.get("name"));
        guineaPigRepository.save(guineaPig);

        GuineaPig gPig = guineaPigRepository.findTopByIdIsNotNullOrderByIdDesc().orElse(guineaPig);
        JSONObject response = new JSONObject();
        response.put("name", gPig.getName());

        return response.toString();
    }

}
