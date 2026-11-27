
from typing import List, Dict, Any


class DesignVariationsGenerator:

    def __init__(self):
        self.themes = [
            {
                "variation_id": "var_1",
                "title": "Closest Match",
                "description": "Faithful layout with a familiar design.",
                "primary_color": "#3B82F6",
                "background_color": "#0A0F1D",
                "surface_color": "#172033",
                "text_color": "#FFFFFF",
                "radius": "8px",
                "style": "Exact Match"
            },
            {
                "variation_id": "var_2",
                "title": "Modern & Minimal",
                "description": "Clean layout with spacious cards and simple typography.",
                "primary_color": "#6366F1",
                "background_color": "#F8FAFC",
                "surface_color": "#FFFFFF",
                "text_color": "#111827",
                "radius": "18px",
                "style": "Minimalist"
            },
            {
                "variation_id": "var_3",
                "title": "Creative & Premium",
                "description": "Premium dark interface with vibrant purple accents.",
                "primary_color": "#A78BFA",
                "background_color": "#1E1B4B",
                "surface_color": "#312E81",
                "text_color": "#FFFFFF",
                "radius": "22px",
                "style": "Premium"
            }
        ]

    def _generate_html(self, app_title: str, theme: Dict[str, Any]) -> str:
        return f"""<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>{app_title}</title>
<style>
* {{ box-sizing: border-box; }}
body {{
    margin: 0;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    font-family: Arial, sans-serif;
    background: {theme['background_color']};
    color: {theme['text_color']};
}}
.container {{
    width: 90%;
    max-width: 380px;
    padding: 30px;
    border-radius: {theme['radius']};
    background: {theme['surface_color']};
}}
h1 {{ margin-bottom: 8px; }}
p {{ opacity: 0.75; }}
input {{
    width: 100%;
    padding: 14px;
    margin: 10px 0;
    border: 1px solid #88888855;
    border-radius: 8px;
}}
button {{
    width: 100%;
    padding: 14px;
    margin-top: 15px;
    border: none;
    border-radius: 8px;
    background: {theme['primary_color']};
    color: white;
    font-weight: bold;
    cursor: pointer;
}}
</style>
</head>
<body>
<div class="container">
    <h1>{app_title}</h1>
    <p>Welcome back! Please sign in.</p>
    <input type="email" placeholder="Email address">
    <input type="password" placeholder="Password">
    <button>Sign In</button>
</div>
</body>
</html>"""

    def _generate_compose(self, app_title: str, theme: Dict[str, Any]) -> str:
        primary = theme["primary_color"].replace("#", "")
        background = theme["background_color"].replace("#", "")
        surface = theme["surface_color"].replace("#", "")

        return f'''package com.Snap2UI.generated

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GeneratedScreen() {{
    var email by remember {{ mutableStateOf("") }}
    var password by remember {{ mutableStateOf("") }}

    val primary = Color(0xFF{primary})
    val background = Color(0xFF{background})
    val surface = Color(0xFF{surface})

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(surface, RoundedCornerShape(20.dp))
                .padding(24.dp)
        ) {{
            Text("{app_title}", fontSize = 28.sp, color = Color.White)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Welcome back! Please sign in.", color = Color.LightGray)

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {{ email = it }},
                label = {{ Text("Email address") }},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {{ password = it }},
                label = {{ Text("Password") }},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {{}},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = primary)
            ) {{
                Text("Sign In")
            }}
        }}
    }}
}}
'''

    def generate_variations(self, app_title: str) -> List[Dict[str, Any]]:
        variations = []

        for theme in self.themes:
            variation = theme.copy()
            variation["app_title"] = app_title
            variation["components"] = [
                {"type": "text", "text": app_title},
                {"type": "text", "text": "Welcome back! Please sign in."},
                {"type": "input", "placeholder": "Email address"},
                {"type": "input", "placeholder": "Password"},
                {"type": "button", "text": "Sign In"}
            ]
            variation["html_code"] = self._generate_html(app_title, theme)
            variation["compose_code"] = self._generate_compose(app_title, theme)
            variations.append(variation)

        return variations
