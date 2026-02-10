# College AI Chatbot - Quick Start Script
# This script helps you run the application quickly

Write-Host "╔═══════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║       🎓 COLLEGE AI CHATBOT - Quick Start Setup          ║" -ForegroundColor Cyan
Write-Host "╚═══════════════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Check Java installation
Write-Host "🔍 Checking Java installation..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✅ Java found: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Java not found! Please install Java 17 or higher." -ForegroundColor Red
    Write-Host "Download from: https://adoptium.net/" -ForegroundColor Yellow
    exit 1
}

# Check Maven installation
Write-Host "🔍 Checking Maven installation..." -ForegroundColor Yellow
try {
    $mvnVersion = mvn -version 2>&1 | Select-String "Apache Maven"
    Write-Host "✅ Maven found: $mvnVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Maven not found! Please install Maven." -ForegroundColor Red
    Write-Host "Download from: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    exit 1
}

Write-Host ""
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "API KEY CONFIGURATION" -ForegroundColor White
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

# Check if API key is set
if ($env:GEMINI_API_KEY) {
    Write-Host "✅ GEMINI_API_KEY environment variable is set!" -ForegroundColor Green
} else {
    Write-Host "⚠️  GEMINI_API_KEY not found!" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "You have two options:" -ForegroundColor White
    Write-Host "1. Set environment variable (Recommended):" -ForegroundColor Cyan
    Write-Host "   `$env:GEMINI_API_KEY='your-api-key-here'" -ForegroundColor Gray
    Write-Host ""
    Write-Host "2. Edit src/main/resources/application.yml" -ForegroundColor Cyan
    Write-Host "   and replace 'your-api-key-here' with your actual key" -ForegroundColor Gray
    Write-Host ""
    Write-Host "Get your free API key from:" -ForegroundColor White
    Write-Host "https://makersuite.google.com/app/apikey" -ForegroundColor Blue
    Write-Host ""
    
    $continue = Read-Host "Do you want to set the API key now? (y/n)"
    if ($continue -eq 'y') {
        $apiKey = Read-Host "Enter your Gemini API key"
        $env:GEMINI_API_KEY = $apiKey
        Write-Host "✅ API key set for this session!" -ForegroundColor Green
        Write-Host ""
    }
}

Write-Host ""
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "BUILDING PROJECT" -ForegroundColor White
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

Write-Host "📦 Running: mvn clean install..." -ForegroundColor Yellow
mvn clean install

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Build successful!" -ForegroundColor Green
} else {
    Write-Host "❌ Build failed! Check errors above." -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "STARTING APPLICATION" -ForegroundColor White
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

Write-Host "🚀 Starting Spring Boot application..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Once started, access:" -ForegroundColor White
Write-Host "  Frontend: http://localhost:8080" -ForegroundColor Cyan
Write-Host "  API:      http://localhost:8080/api/chat" -ForegroundColor Cyan
Write-Host ""
Write-Host "Press Ctrl+C to stop the application" -ForegroundColor Yellow
Write-Host ""

mvn spring-boot:run
