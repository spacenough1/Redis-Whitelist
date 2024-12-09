---

## Redis-Based Whitelist Plugin for PaperSpigot ⚡  
_Unfinished (and maybe forgotten), but Redis enthusiasts will appreciate the vibe._

### Overview 📝  
This is a Paper/Spigot plugin designed to manage your server’s whitelist using **Redis** as the backbone. It’s all about speed, scalability, and, let’s be honest, a love for Redis. While the project isn’t finished, it’s packed with potential to take your whitelist management to the next level.  

### Features (or plans for them) 🚀  
- **Redis integration:** Leverages Redis for lightning-fast whitelist lookups.  
- **Scalability:** Designed with performance in mind for large servers or networks.  
- **Real-time updates:** Instant sync of whitelist changes across multiple servers.  
- **Command management:** A suite of commands to add, remove, and list whitelisted players (mostly functional).  

### Requirements 📋  
- **Minecraft Server:** Paper/Spigot 1.20+ (1.20.4 TESTED)  
- **Java:** 17+  
- **Redis:** A running Redis instance (local or remote).  
- **Gradle:** For building the plugin.  

### Building the Plugin ⚒️  
1. Clone the repository:  
   ```bash
   git clone https://github.com/spacenough1/Redis-Whitelist.git
   cd Redis-Whitelist
   ```  

2. Build the plugin with Gradle (Shadow):  
   ```bash
   ./gradlew shadowJar
   ```  

3. The plugin JAR will be located in `build/libs/` with a name like `Hades-WhiteList-<version>.jar`.  

### Installation & Setup 🛠️  
1. Place the JAR file into your server's `plugins` folder.  
2. Configure the `config.yml` with your Redis connection details:  
   ```yaml
   redis-host: "127.0.0.1"
   redis-port: 6379
   redis-password: "xxx"
   ```  
3. Restart your server and enjoy (or debug, because it’s unfinished).  

### Contributing 🤝  
Want to help finish this project? Fork it, send PRs, or just leave a star and some feedback. Redis enthusiasts are especially welcome.  

---
