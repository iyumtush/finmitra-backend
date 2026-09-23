package com.finmitra.service;

import com.finmitra.dto.AIInsightResponse;
import com.finmitra.dto.ChatRequest;
import com.finmitra.dto.ChatResponse;
import com.finmitra.dto.ReceiptParseRequest;
import com.finmitra.dto.ReceiptParseResponse;

public interface AIService {
    AIInsightResponse generateInsights(String userEmail);
    ChatResponse chatWithAI(String userEmail, ChatRequest request);
    ReceiptParseResponse parseReceipt(String userEmail, ReceiptParseRequest request);
}
