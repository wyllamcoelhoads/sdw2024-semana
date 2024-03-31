package me.dio.sdw24.adapters.out;

import me.dio.sdw24.domain.ports.GeneraTiveAiApi;
import org.springframework.cloud.openfeign.FeignClient;

/*Ele definiu o titulo da classe dele de OpenAiChatApi eu define como ChatCompletion igual o sugerido no link
*  https://platform.openai.com/docs/api-reference/chat/create*/
@FeignClient(name = "chatCompletion", url = "${openai.base-url}")
public interface ChatCompletion extends GeneraTiveAiApi {

    @Override
    default  String generateContent(String objective, String context){
        return null;
    }
}
