package me.dio.sdw24.domain.model;

public record Champions(
        Long id,
        String nome,
        String role,
        String lore,
        String imageUrl
) {
    public String generateContextByQuestion(String question){
        return """
    Nome do Campeão: %s
    Função: %s
    Lore (Historia): %s
    """.formatted(question, this.nome, this.role, this.lore);
    }
}
