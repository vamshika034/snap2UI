from typing import List, Dict, Any

class ComposeGenerator:
    """
    Generates Jetpack Compose Kotlin code from detected UI components.
    """
    def __init__(self):
        pass

    def generate_compose(self, components: List[Dict[str, Any]]) -> str:
        code_lines = [
            'package com.Snap2UI.app.ui.generated',
            '',
            'import androidx.compose.foundation.background',
            'import androidx.compose.foundation.layout.*',
            'import androidx.compose.material3.*',
            'import androidx.compose.runtime.*',
            'import androidx.compose.ui.Alignment',
            'import androidx.compose.ui.Modifier',
            'import androidx.compose.ui.graphics.Color',
            'import androidx.compose.ui.unit.dp',
            'import androidx.compose.ui.unit.sp',
            '',
            '@Composable',
            'fun ReconstructedScreen() {',
            '    Box(',
            '        modifier = Modifier',
            '            .fillMaxSize()',
            '            .background(Color(0xFF0A0F1D))',
            '            .padding(16.dp)',
            '    ) {',
            '        Column(',
            '            modifier = Modifier.fillMaxSize(),',
            '            verticalArrangement = Arrangement.spacedBy(16.dp),',
            '            horizontalAlignment = Alignment.CenterHorizontally',
            '        ) {'
        ]

        for comp in components:
            c_type = comp.get("type", "text_view")
            text = comp.get("text", "UI Element")

            if c_type == "button":
                code_lines.append(f'            Button(')
                code_lines.append(f'                onClick = {{ }},')
                code_lines.append(f'                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))')
                code_lines.append(f'            ) {{')
                code_lines.append(f'                Text("{text}", color = Color.White)')
                code_lines.append(f'            }}')
            elif c_type == "text_field":
                code_lines.append(f'            var text_{comp.get("id", "field")} by remember {{ mutableStateOf("") }}')
                code_lines.append(f'            OutlinedTextField(')
                code_lines.append(f'                value = text_{comp.get("id", "field")},')
                code_lines.append(f'                onValueChange = {{ text_{comp.get("id", "field")} = it }},')
                code_lines.append(f'                placeholder = {{ Text("{text}") }}')
                code_lines.append(f'            )')
            else:
                code_lines.append(f'            Text(')
                code_lines.append(f'                text = "{text}",')
                code_lines.append(f'                color = Color.White,')
                code_lines.append(f'                fontSize = 16.sp')
                code_lines.append(f'            )')

        code_lines.extend([
            '        }',
            '    }',
            '}'
        ])
        return "\n".join(code_lines)
