from typing import List, Dict, Any

class MultiScreenGenerator:
    """
    Generates complete multi-screen application specifications with shared design systems,
    consistent colors/typography, and screen navigation flows.
    """
    def __init__(self):
        pass

    def generate_app_screens(self, app_name: str, app_type: str) -> Dict[str, Any]:
        screens = [
            {"screen_id": "login", "title": f"{app_name} - Login", "type": "Authentication", "components_count": 4},
            {"screen_id": "register", "title": f"{app_name} - Register", "type": "Authentication", "components_count": 5},
            {"screen_id": "home", "title": f"{app_name} - Home Dashboard", "type": "Dashboard", "components_count": 8},
            {"screen_id": "listing", "title": f"{app_name} - Item Listing", "type": "Catalog", "components_count": 6},
            {"screen_id": "details", "title": f"{app_name} - Item Details", "type": "Details", "components_count": 7},
            {"screen_id": "cart", "title": f"{app_name} - Shopping Cart", "type": "Checkout", "components_count": 5},
            {"screen_id": "payment", "title": f"{app_name} - Payment Checkout", "type": "Transaction", "components_count": 6},
            {"screen_id": "profile", "title": f"{app_name} - User Profile", "type": "Settings", "components_count": 5}
        ]

        design_system = {
            "primary_color": "#3B82F6",
            "secondary_color": "#8B5CF6",
            "background_color": "#0A0F1D",
            "surface_color": "#131B31",
            "typography": "Material 3 Scale",
            "border_radius": "16dp rounded cards"
        }

        return {
            "app_name": app_name,
            "app_type": app_type,
            "design_system": design_system,
            "screens": screens,
            "navigation_flow": "Login -> Home -> Listing -> Details -> Cart -> Payment -> Profile"
        }
