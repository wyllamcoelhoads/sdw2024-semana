package me.dio.sdw24.adapters.out;

import feign.RequestInterceptor;
import me.dio.sdw24.domain.ports.GeneraTiveAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*Ele definiu o titulo da classe dele de OpenAiChatApi eu define como ChatCompletion igual o sugerido no link
*  https://platform.openai.com/docs/api-reference/chat/create*/
@FeignClient(name = "chatCompletion", url = "${openai.base-url}", configuration = ChatCompletion.Config.class)
public interface ChatCompletion extends GeneraTiveAiApi {
    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionReq req);
    @Override
    default  String generateContent(String objective, String context){
        String model = "gpt-3.5-turbo";
        List<Message> messages = List.of(
            new Message("system", objective),
            new Message("user", context)
        );
        OpenAiChatCompletionReq req = new OpenAiChatCompletionReq(model, messages);
        OpenAiChatCompletionResp resp = chatCompletion(req);
        return resp.choices().getFirst().message().content();
    }
    /* PARA P REQUESTE
     *
     * '{
     *     "model": "gpt-3.5-turbo",
     *     "messages": [
     *       {
     *         "role": "system",
     *         "content": "You are a helpful assistant."
     *       },
     *       {
     *         "role": "user",
     *         "content": "Hello!"
     *       }
     *     ]
     *   }'


     * PARA A RESPOSE
     *
     * {
     *  "choices": [{
     *  "message": {
     *      "role": "assistant",
     *      "content": "\n\nHello there, how may I assist you today?",
     *    }
     *  }],
     * }
     */

    record  OpenAiChatCompletionReq(String model, List<Message> messages) { }
    record  Message(String role, String content) { }

    record  OpenAiChatCompletionResp(String model, List<Choice> choices) { }
    record  Choice(Message message) { }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${OPENAI_API_KEY}") String apikey) {
            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION,
                    "Bearer %s".formatted(apikey));
        }
    }
}
