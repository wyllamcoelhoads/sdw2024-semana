package me.dio.sdw24.adapters.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw24.application.AskChampionUseCase;
import me.dio.sdw24.application.ListChampionUseCase;
import me.dio.sdw24.domain.model.Champions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Champions", description = "Endpoints do dominio dos campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record AskChampionRestController(AskChampionUseCase useCase) {
    @PostMapping("/{championId}/ask")
    public AskChampionResponse askChampion(
            @PathVariable  Long championId,
            @RequestBody AskChampionRequest request) {
        String answer = useCase.askChampion(championId, request.question());

        return new AskChampionResponse(answer);
    }

    public record  AskChampionRequest(String question) {};
    public record  AskChampionResponse(String answer) {};

}
