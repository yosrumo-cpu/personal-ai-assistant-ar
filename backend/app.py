from fastapi import FastAPI
from pydantic import BaseModel
import os
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI(title="Personal AI Assistant API")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


class ChatRequest(BaseModel):
    message: str


@app.get("/")
def read_root():
    return {"message": "Personal AI Assistant API is running"}


@app.post("/chat")
def chat(request: ChatRequest):
    text = request.message.lower()

    if "مهمة" in text or "تذكير" in text or "ذكرني" in text:
        reply = "حسناً، سأضيف المهمة إلى قائمة مهامك وأذكّرك بها في الوقت المناسب."
    elif "ملاحظة" in text or "ملحوظة" in text:
        reply = "تم حفظ ملاحظتك في قسم الملاحظات." 
    elif "مرحبا" in text or "السلام" in text or "اهلا" in text:
        reply = "مرحباً! أنا مساعدك الشخصي، كيف يمكنني مساعدتك اليوم؟"
    elif "خطة" in text or "جدول" in text:
        reply = "أستطيع تنظيم جدولك اليومي بناءً على أولوياتك، فقط أخبرني بمهامك." 
    else:
        reply = "أستطيع مساعدتك في تنظيم مهامك، الملاحظات، والتخطيط اليومي."

    return {"reply": reply}


@app.get("/health")
def health():
    return {"status": "ok"}
