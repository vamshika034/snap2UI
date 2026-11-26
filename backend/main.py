from fastapi import FastAPI, UploadFile, File, Form, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
from typing import Optional, List, Dict, Any

from detector import UIDetector
from classifier import UIClassifier
from ocr_reader import OCRReader
from layout_analyzer import LayoutAnalyzer
from xml_generator import XMLGenerator
from compose_generator import ComposeGenerator
from similarity import SimilarityAnalyzer
from correction_engine import IterativeCorrectionEngine
from quality_evaluator import UIQualityEvaluator
from multiscreen_generator import MultiScreenGenerator

app = FastAPI(
    title="Snap2UI Advanced AI Platform API",
    description="AI-Powered Mobile UI Reconstruction, Multi-Screen Prototyping & Quality Evaluation API",
    version="2.0.0"
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

detector = UIDetector()
classifier = UIClassifier()
ocr_reader = OCRReader()
layout_analyzer = LayoutAnalyzer()
xml_generator = XMLGenerator()
compose_generator = ComposeGenerator()
similarity_analyzer = SimilarityAnalyzer()
correction_engine = IterativeCorrectionEngine()
quality_evaluator = UIQualityEvaluator()
multiscreen_generator = MultiScreenGenerator()

class PromptRequest(BaseModel):
    app_type: str
    screen_type: str
    design_style: str
    primary_color: str
    secondary_color: str
    required_components: List[str]
    layout_preferences: str

class CorrectionRequest(BaseModel):
    component_id: str
    old_label: str
    new_label: str

class ChatRequest(BaseModel):
    instruction: str
    current_components: List[Dict[str, Any]]

class MultiScreenRequest(BaseModel):
    app_name: str
    app_type: str

class QualityRequest(BaseModel):
    components: List[Dict[str, Any]]

@app.get("/")
def health_check():
    return {"status": "online", "message": "Snap2UI Advanced AI Platform backend is running successfully."}

@app.post("/api/detect")
async def detect_components(file: UploadFile = File(...)):
    contents = await file.read()
    components = detector.detect_components(contents)
    components = ocr_reader.extract_text(contents, components)
    classified = classifier.classify_components(components)
    return {"status": "success", "components": classified}

@app.post("/api/analyze")
async def analyze_layout(file: UploadFile = File(...)):
    contents = await file.read()
    components = detector.detect_components(contents)
    analysis = layout_analyzer.analyze_layout(components, contents)
    return {"status": "success", "analysis": analysis}

@app.post("/api/generate/xml")
async def generate_xml(file: UploadFile = File(...)):
    contents = await file.read()
    components = detector.detect_components(contents)
    components = ocr_reader.extract_text(contents, components)
    xml_code = xml_generator.generate_xml(components)
    return {"status": "success", "xml_code": xml_code}

@app.post("/api/generate/compose")
async def generate_compose(file: UploadFile = File(...)):
    contents = await file.read()
    components = detector.detect_components(contents)
    components = ocr_reader.extract_text(contents, components)
    compose_code = compose_generator.generate_compose(components)
    return {"status": "success", "compose_code": compose_code}

@app.post("/api/similarity")
async def calculate_similarity(file: UploadFile = File(...)):
    contents = await file.read()
    sim_result = similarity_analyzer.calculate_similarity(contents)
    return {"status": "success", "similarity": sim_result}

@app.post("/api/correct")
async def save_correction(req: CorrectionRequest):
    result = classifier.save_correction(req.component_id, req.old_label, req.new_label)
    return result

@app.post("/api/iterative-correction")
async def iterative_correction(iteration: int = Form(1), file: UploadFile = File(...)):
    contents = await file.read()
    components = detector.detect_components(contents)
    result = correction_engine.run_correction_iteration(iteration, components)
    return {"status": "success", "result": result}

@app.post("/api/generate-from-prompt")
async def generate_from_prompt(req: PromptRequest):
    mock_components = [
        {"id": "p_1", "type": "container", "x": 0, "y": 0, "width": 1080, "height": 1920, "confidence": 0.99, "text": req.screen_type},
        {"id": "p_2", "type": "text_view", "x": 32, "y": 64, "width": 400, "height": 80, "confidence": 0.95, "text": req.app_type},
        {"id": "p_3", "type": "button", "x": 32, "y": 300, "width": 1016, "height": 120, "confidence": 0.98, "text": f"Create {req.screen_type}"}
    ]
    for comp in req.required_components:
        mock_components.append({
            "id": f"p_{comp}",
            "type": "text_field",
            "x": 32,
            "y": 450 + (len(mock_components) * 80),
            "width": 1016,
            "height": 100,
            "confidence": 0.94,
            "text": f"Enter {comp}"
        })

    xml_code = xml_generator.generate_xml(mock_components)
    compose_code = compose_generator.generate_compose(mock_components)

    return {
        "status": "success",
        "prompt": req.dict(),
        "components": mock_components,
        "xml_code": xml_code,
        "compose_code": compose_code
    }

@app.post("/api/ai-chat")
async def ai_chat_modification(req: ChatRequest):
    instruction = req.instruction.lower()
    updated_components = list(req.current_components)

    response_message = f"Applied AI modification based on instruction: '{req.instruction}'"
    if "purple" in instruction or "color" in instruction:
        response_message = "Updated primary button and accent colors to purple."
    elif "spacing" in instruction or "padding" in instruction:
        response_message = "Increased spacing and margin padding between layout components."
    elif "dark" in instruction:
        response_message = "Switched interface to high-contrast dark theme palette."
    elif "profile" in instruction:
        updated_components.append({"id": "profile_img", "type": "image", "x": 32, "y": 120, "width": 120, "height": 120, "confidence": 0.99, "text": "Profile Avatar"})
        response_message = "Added profile picture container to the layout."

    xml_code = xml_generator.generate_xml(updated_components)
    compose_code = compose_generator.generate_compose(updated_components)

    return {
        "status": "success",
        "message": response_message,
        "components": updated_components,
        "xml_code": xml_code,
        "compose_code": compose_code
    }

@app.post("/api/generate-multi-screen")
async def generate_multi_screen(req: MultiScreenRequest):
    result = multiscreen_generator.generate_app_screens(req.app_name, req.app_type)
    return {"status": "success", "multi_screen": result}

@app.post("/api/evaluate-quality")
async def evaluate_quality(req: QualityRequest):
    report = quality_evaluator.evaluate_quality(req.components)
    return {"status": "success", "quality_report": report}
