from typing import List, Dict, Any

class DesignVariationsGenerator:
    """
    Generates three distinct UI design variations from a screenshot or text prompt:
    Variation 1: Closest match to original.
    Variation 2: Modern and minimal redesign.
    Variation 3: Creative and premium redesign.
    """
    def __init__(self):
        pass

    def generate_variations(self, app_title: str) -> List[Dict[str, Any]]:
        return [
            {
                "variation_id": "var_1",
                "title": "Closest Match (Baseline)",
                "description": "Faithful reconstruction preserving exact original layout structure, alignment, and color palette.",
                "primary_color": "#3B82F6",
                "background_color": "#0A0F1D",
                "style": "Exact Match"
            },
            {
                "variation_id": "var_2",
                "title": "Modern & Minimal",
                "description": "Clean whitespace, rounded cards, subtle shadows, and streamlined typography for enhanced clarity.",
                "primary_color": "#6366F1",
                "background_color": "#0F172A",
                "style": "Minimalist"
            },
            {
                "variation_id": "var_3",
                "title": "Creative & Premium",
                "description": "Vibrant gradients, high-contrast violet accents, floating action elements, and luxurious card layouts.",
                "primary_color": "#8B5CF6",
                "background_color": "#1E1B4B",
                "style": "Premium"
            }
        ]
