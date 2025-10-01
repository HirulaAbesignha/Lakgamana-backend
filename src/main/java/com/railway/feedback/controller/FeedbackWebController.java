package com.railway.feedback.controller;

import com.railway.feedback.entity.Feedback;
import com.railway.feedback.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackWebController {

    private final FeedbackService feedbackService;

    public FeedbackWebController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedbackForm";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback, Model model) {
        feedbackService.addFeedback(feedback);
        model.addAttribute("message", "Feedback submitted successfully!");
        model.addAttribute("feedback", new Feedback());
        return "feedbackForm";
    }

    @GetMapping("/list")
    public String listFeedback(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "feedbackList";
    }
}
