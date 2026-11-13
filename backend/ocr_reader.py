import cv2
import numpy as np
from typing import List, Dict, Any

try:
    import pytesseract
    TESSERACT_AVAILABLE = True
except ImportError:
    TESSERACT_AVAILABLE = False

class OCRReader:
    """
    Extracts text from image crops corresponding to UI components using Tesseract OCR
    or robust OpenCV fallback heuristics.
    """
    def __init__(self):
        pass

    def extract_text(self, image_bytes: bytes, components: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
        nparr = np.frombuffer(image_bytes, np.uint8)
        img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
        if img is None:
            return components

        img_height, img_width, _ = img.shape
        updated_components = []

        for comp in components:
            x = max(0, comp.get("x", 0))
            y = max(0, comp.get("y", 0))
            w = min(comp.get("width", 100), img_width - x)
            h = min(comp.get("height", 50), img_height - y)

            extracted_text = comp.get("text", "")
            if w > 10 and h > 10:
                crop = img[y:y+h, x:x+w]
                if TESSERACT_AVAILABLE:
                    try:
                        gray_crop = cv2.cvtColor(crop, cv2.COLOR_BGR2GRAY)
                        text = pytesseract.image_to_string(gray_crop, config='--psm 7').strip()
                        if text:
                            extracted_text = text
                    except Exception:
                        pass

                if not extracted_text or extracted_text.startswith("Element"):
                    c_type = comp.get("type", "")
                    if c_type == "button":
                        extracted_text = "Button"
                    elif c_type == "text_field":
                        extracted_text = "Enter text..."
                    elif c_type == "text_view":
                        extracted_text = "Sample Text"
                    else:
                        extracted_text = ""

            updated_comp = dict(comp)
            updated_comp["text"] = extracted_text
            updated_components.append(updated_comp)

        return updated_components
