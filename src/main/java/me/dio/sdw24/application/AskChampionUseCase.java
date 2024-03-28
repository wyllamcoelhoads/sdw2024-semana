package me.dio.sdw24.application;

import me.dio.sdw24.domain.exeption.ChampionNotFoundException;
import me.dio.sdw24.domain.model.Champions;
import me.dio.sdw24.domain.ports.ChampionsRepository;

import java.util.List;

public record AskChampionUseCase(ChampionsRepository repository) {

    public String askChampion(Long championId, String question){
        Champions champions = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId)) ;

        //TODO: Evoluir a logica de negocio para considerar a integração com IAs Generativas.

        return "";
    }
}