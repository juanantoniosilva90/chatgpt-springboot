package dev.silva.chatgptspringboot;

import io.github.flashvayne.chatgpt.dto.ChatRequest;
import io.github.flashvayne.chatgpt.dto.ChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbot")
public class HelloGPTController implements InitializingBean {



    @Autowired
    private ChatgptService chatgptService;

    @Override
    public void afterPropertiesSet() {System.out.println("*****Iniciando Chat GTP Boot*****");}

    @GetMapping("/chat")
    public String chatWith(@RequestParam String message) {
        System.out.println(message);
        return chatgptService.sendMessage(message);
    }




    @GetMapping("/prompt")
    public ChatResponse prompt(@RequestParam String message) {

        //Número maximo de token generados. Sujeto a ls longitud permitida (2048)
        Integer maxTokens = 300;

        // Chat de lenguaje natural. Da Vinci es el modelo más completo, y Ada el más rápido.
        String model = "text-davinci-003";

        // Valores máximos utilizados como ejemplo.
        Double temperature = 0.5;

        //ejemplo de valor máximo y alternancia. Nunca use ambos
        Double topP = 1.0;

        ChatRequest chatRequest = new ChatRequest(model, message, maxTokens, temperature, topP);
        ChatResponse res = chatgptService.sendChatRequest(chatRequest);
        System.out.println("Response was: " + res.toString());
        return res;
    }
}
