package com.example.springAI;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

    private ChatClient chatClient;

    public OpenAIController(OpenAiChatModel chatModel){
        this.chatClient=chatClient.create(chatModel);
    }

    @GetMapping("/api/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message){
//        String response=chatClient
//                .prompt(message)
//                .call()
//                .content();
//        return ResponseEntity.ok(response);
        ChatResponse chatResponse=chatClient
                .prompt(message)
                .call()
                .chatResponse();//for meta data

        System.out.println(chatResponse.getMetadata().getModel());

        String response=chatResponse
                .getResult()
                .getOutput()
                .getText();
        return ResponseEntity.ok(response);
    }
}
