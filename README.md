# مساعدي الشخصي

تطبيق Android عربي مبني كـ MVP لمساعد شخصي ذكي.

## ماذا يحتوي التطبيق
- دردشة عربية
- قسم المهام
- قسم الملاحظات
- واجهة عربية جاهزة
- قابلة للتوسعة مع الذكاء الاصطناعي

## التقنية المستخدمة
- Android Native (Kotlin)
- ViewPager + Fragments
- Material 3
- FastAPI backend (لـ OpenAI / Claude)
- Firebase (اختياري لاحقاً)

## هيكل المشروع
- app/ : تطبيق Android
- backend/ : خادم API للذكاء الاصطناعي

## التشغيل
### تشغيل التطبيق Android
1. افتح المشروع في Android Studio
2. انتظر تحميل Gradle
3. اختر جهاز محاكي أو هاتف متصل
4. اضغط Run

### تشغيل الـ backend
```bash
cd backend
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
export OPENAI_API_KEY="your_key_here"
uvicorn app:app --reload --host 0.0.0.0 --port 8000
```

## مثال API
```http
POST http://localhost:8000/chat
Content-Type: application/json

{
  "message": "أريد خطة يومية"
}
```

## ملاحظات
هذا مشروع MVP أولي، وهو جاهز للتوسعة إلى:
- تسجيل دخول
- Firebase Auth
- Firebase Firestore
- OpenAI API
- Voice / Speech
- Reminders / Notifications
- تقويم شخصي
