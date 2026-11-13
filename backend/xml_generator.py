from typing import List, Dict, Any

class XMLGenerator:
    """
    Generates Android XML layout code from detected UI components.
    """
    def __init__(self):
        pass

    def generate_xml(self, components: List[Dict[str, Any]]) -> str:
        xml_lines = [
            '<?xml version="1.0" encoding="utf-8"?>',
            '<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"',
            '    xmlns:app="http://schemas.android.com/apk/res-auto"',
            '    xmlns:tools="http://schemas.android.com/tools"',
            '    android:layout_width="match_parent"',
            '    android:layout_height="match_parent"',
            '    android:background="#0A0F1D">',
            ''
        ]

        for comp in components:
            c_type = comp.get("type", "text_view")
            c_id = comp.get("id", "view")
            text = comp.get("text", "")
            w = comp.get("width", 200)
            h = comp.get("height", 50)
            x = comp.get("x", 16)
            y = comp.get("y", 16)

            if c_type == "button":
                xml_lines.append(f'    <Button')
                xml_lines.append(f'        android:id="@+id/{c_id}"')
                xml_lines.append(f'        android:layout_width="{w}dp"')
                xml_lines.append(f'        android:layout_height="{h}dp"')
                xml_lines.append(f'        android:text="{text}"')
                xml_lines.append(f'        android:backgroundTint="#3B82F6"')
                xml_lines.append(f'        app:layout_constraintStart_toStartOf="parent"')
                xml_lines.append(f'        app:layout_constraintTop_toTopOf="parent"')
                xml_lines.append(f'        android:layout_marginStart="{x}dp"')
                xml_lines.append(f'        android:layout_marginTop="{y}dp" />')
            elif c_type == "text_field":
                xml_lines.append(f'    <EditText')
                xml_lines.append(f'        android:id="@+id/{c_id}"')
                xml_lines.append(f'        android:layout_width="{w}dp"')
                xml_lines.append(f'        android:layout_height="{h}dp"')
                xml_lines.append(f'        android:hint="{text}"')
                xml_lines.append(f'        app:layout_constraintStart_toStartOf="parent"')
                xml_lines.append(f'        app:layout_constraintTop_toTopOf="parent"')
                xml_lines.append(f'        android:layout_marginStart="{x}dp"')
                xml_lines.append(f'        android:layout_marginTop="{y}dp" />')
            else:
                xml_lines.append(f'    <TextView')
                xml_lines.append(f'        android:id="@+id/{c_id}"')
                xml_lines.append(f'        android:layout_width="wrap_content"')
                xml_lines.append(f'        android:layout_height="wrap_content"')
                xml_lines.append(f'        android:text="{text}"')
                xml_lines.append(f'        android:textColor="#FFFFFF"')
                xml_lines.append(f'        app:layout_constraintStart_toStartOf="parent"')
                xml_lines.append(f'        app:layout_constraintTop_toTopOf="parent"')
                xml_lines.append(f'        android:layout_marginStart="{x}dp"')
                xml_lines.append(f'        android:layout_marginTop="{y}dp" />')
            xml_lines.append('')

        xml_lines.append('</androidx.constraintlayout.widget.ConstraintLayout>')
        return "\n".join(xml_lines)
