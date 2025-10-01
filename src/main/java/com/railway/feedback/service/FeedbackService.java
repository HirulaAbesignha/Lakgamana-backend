package com.railway.feedback.service;

import com.railway.feedback.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback addFeedback(Feedback feedback);
    Feedback updateFeedback(Long id, Feedback feedback);
    void deleteFeedback(Long id);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedback();
    List<Feedback> getFeedbackByEmail(String email);
}
