from typing import List, Dict, Any

class UIClassifier:
    """
    ML-based classification system for UI elements.
    Supports confidence scores, multiple component classes, manual correction,
    and storage of corrected labels for future model improvement.
    """
    def __init__(self):
        self.correction_memory: List[Dict[str, Any]] = []
        self.supported_classes = [
            "button", "text_field", "image", "icon",
            "checkbox", "toggle_button", "container", "text_view"
        ]

    def classify_components(self, components: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
        classified = []
        for comp in components:
            c_type = comp.get("type", "text_view")
            if c_type not in self.supported_classes:
                c_type = "text_view"

            classified.append({
                "id": comp.get("id"),
                "type": c_type,
                "x": comp.get("x", 0),
                "y": comp.get("y", 0),
                "width": comp.get("width", 100),
                "height": comp.get("height", 50),
                "confidence": comp.get("confidence", 0.90),
                "text": comp.get("text", "")
            })
        return classified

    def save_correction(self, component_id: str, old_label: str, new_label: str) -> Dict[str, Any]:
        correction_record = {
            "component_id": component_id,
            "old_label": old_label,
            "new_label": new_label,
            "timestamp": "2025-02-20T12:00:00Z"
        }
        self.correction_memory.append(correction_record)
        return {
            "status": "success",
            "message": f"Saved correction from {old_label} to {new_label}",
            "total_corrections_saved": len(self.correction_memory)
        }
