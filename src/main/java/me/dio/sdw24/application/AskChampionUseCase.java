package me.dio.sdw24.application;

import me.dio.sdw24.domain.exeption.ChampionNotFoundException;
import me.dio.sdw24.domain.model.Champions;
import me.dio.sdw24.domain.ports.ChampionsRepository;
import me.dio.sdw24.domain.ports.GeneraTiveAiApi;

import java.util.List;

public record AskChampionUseCase(ChampionsRepository repository, GeneraTiveAiApi genAiApi) {

    public String askChampion(Long championId, String question){

        Champions champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId)) ;
        String context = champion.generateContextByQuestion(question);
        String objective = """
                Atue como um assistente com habilidade de se comportar como os Compeões do League Of Lagends (Lol).
                Responda perguntas perguntas incorporando a personalidade e estilo de um determinado compeão
                Segue a pergunta, o nome do campeão e a sua respectiva lore(historia):
                
                """;

        return genAiApi.generateContent(objective, context);
    }
}