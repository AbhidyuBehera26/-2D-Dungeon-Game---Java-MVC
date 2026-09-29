# 🎮 2D Dungeon Game - Java MVC Template

A 2D Dungeon Crawler game written in Java using Swing and AWT graphics, structured cleanly around the **Model-View-Controller (MVC)** architectural pattern.

---

## 📌 Project Overview

This repository contains a full 2D top-down dungeon crawler game template featuring animated sprites, enemy AI pathfinding/chase logic, combat mechanics, potion pickups, level progression, and state save/load capabilities.

### 🎯 Key Features
- **MVC Architecture**: Decoupled Model (game state), Viewer (rendering), and Controller (user input).
- **Player Mechanics**: Player movement, rotation, health bar, sword attacks, and character selection (Red Knight / Blue Knight).
- **Enemies & Combat**: Dynamic enemy spawns (Goblins and Skeletons) with health and potion drop mechanics.
- **Save & Load System**: Quick-save game state to `savegame.txt` using key `F5`.
- **Unit Tests**: Built-in test suite for vector and coordinate math in `src/util/UnitTests.java`.
- **Documentation**: Comprehensive UML diagrams and game development report stored under `docs/`.

---

## 📁 Repository Structure

```
├── src/                      # Java Source Code
│   ├── Controller.java       # User input handling (Keyboard & Mouse)
│   ├── MainWindow.java       # Swing frame entry point & game loop launcher
│   ├── Model.java            # Game physics, entities, collision & state
│   ├── Viewer.java           # Graphical rendering engine (Graphics2D)
│   └── util/
│       ├── GameObject.java   # Base class for game entities & sprites
│       ├── Point3f.java      # 3D spatial coordinate representation
│       ├── Vector3f.java     # 3D vector math utility class
│       └── UnitTests.java    # Unit tests for vector math & game utilities
├── res/                      # Game Assets & Textures
│   ├── startscreen.png
│   ├── realistic_dungeon.png
│   ├── red_knight.png
│   ├── blue_knight.png
│   ├── goblin.png
│   ├── skeleton.png
│   ├── sword.png
│   ├── potion.png
│   └── blankSprite.png
├── docs/                     # Design & Project Documentation
│   ├── Class diagram.png
│   ├── new_diagram.png
│   ├── new_diagram.uxf
│   └── Game Development Report.pdf
├── .classpath                # Eclipse Classpath Configuration
├── .project                  # Eclipse Project Metadata
├── .gitignore                # Git Ignore Rules
└── README.md                 # Project Documentation
```

---

## 🕹️ Controls & Hotkeys

| Action | Control / Key |
| :--- | :--- |
| **Move Up / Down / Left / Right** | `W` / `S` / `A` / `D` or Arrow Keys |
| **Attack / Shoot** | Spacebar / Mouse Click |
| **Quick Save Game** | `F5` |
| **Pause Game** | `P` |

---

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK 8 or higher)** installed.
- Any Java IDE (Eclipse, VS Code, IntelliJ IDEA) or command terminal.

### Running from IDE (Eclipse / VS Code / IntelliJ)
1. Open or import the project directory as a Java project.
2. Ensure `src/` is set as the Source Folder and `bin/` as the Output Folder.
3. Run `src/MainWindow.java` as a **Java Application**.

### Running from Command Line
```bash
# Compile Java source files into bin directory
mkdir -p bin
javac -d bin src/*.java src/util/*.java

# Run the game
java -cp bin MainWindow
```

---

## 📄 Documentation

For full architectural details, design choices, and class relationships, refer to:
- [Class Diagram](docs/Class%20diagram.png)
- [Game Development Report](docs/Game%20Development%20Report.pdf)

---

## 📝 License
This project is open-source and intended for educational and template usage.
