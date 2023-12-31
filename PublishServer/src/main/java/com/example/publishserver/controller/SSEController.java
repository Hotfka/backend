package com.example.publishserver.controller;

import com.example.publishserver.service.SSEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/SSE")
@RequiredArgsConstructor
public class SSEController {
    private final SSEService sseService;

    @GetMapping(value = "/subscribe/{userName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String userName) {
        return sseService.subscribe(userName);
    }

    @PostMapping("/send-message/{userName}")
    public void sendMessageEvent(@PathVariable String userName) {
        sseService.notify(userName, "messageEvent");
    }

    @PostMapping("/send-reaction/{userName}")
    public void sendReactionEvent(@PathVariable String userName) {
        sseService.notify(userName, "reactionEvent");
    }


}