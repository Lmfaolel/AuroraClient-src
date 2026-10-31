# Aurora Client

Aurora is a feature-rich, high-performance Fabric client mod for Minecraft 1.21.1 designed for competitive PvP, smoothness, and visual customization.

## Features

- **Performance & Smoothness:**
  - Optimized input latency and polling
  - Refined crystal and anchor placement optimizations
  - Smooth camera panning and custom zoom engine
  - Dynamic FPS and frame pacing options

- **HUD & Visual Customization:**
  - Fully customizable HUD modules (FPS, CPS, Keystrokes, Armor Status, Ping, etc.)
  - Custom crosshairs and crosshair animations
  - Glass and block overlay shaders
  - Damage tinting, totem pop animations, and custom hit effects

- **Cosmetics Engine:**
  - Custom cape renderer (supporting animated, static, and official capes)
  - 3D wing models and textures
  - Custom mace and particle trail effects

- **Quality of Life:**
  - Built-in toggle sprint / sneak
  - Tier tagger and player inspection
  - Streamlined settings GUI and profile management

---

## Technical Specifications

- **Minecraft Version:** `1.21.1`
- **Fabric Loader:** `>= 0.16.0`
- **Fabric API:** `0.102.0+1.21.1`
- **Java Version:** `Java 21`
- **Mappings:** Yarn `1.21.1+build.1`

---

## Building from Source

### Prerequisites

- [Java Development Kit (JDK) 21](https://adoptium.net/) or higher
- [Git](https://git-scm.com/)

### Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/Lmfaolel/AuroraClient-src.git
   cd AuroraClient-src
   ```

2. Build the mod using Gradle:
   ```bash
   gradle build
   ```

3. The built JAR file will be located in:
   ```text
   build/libs/aurora-1.0.0.jar
   ```

---

## Project Structure

- `dev/` - Primary Aurora client source code (modules, render engine, GUI, cosmetics, mixins)
- `io/` - Auxiliary input/output and library integrations
- `assets/` - Client textures, fonts, icons, and shaders
- `META-INF/` - Fabric mod metadata and bundled libraries

---

## License

This project includes third-party libraries and modules licensed under their respective open-source terms. See the individual `LICENSE_*` files in the root directory for details.
