require "json"

Pod::Spec.new do |s|
    package = JSON.parse(File.read(File.join(File.dirname(__FILE__), "package.json")))
    s.name         = "RNYamap"
    s.version      = package["version"]
    s.summary      = package["description"]
    s.homepage     = "vvdev.ru"
    s.license      = "MIT"
    s.author       = { package["author"]["name"] => package["author"]["email"] }
    s.platform     = :ios, "9.0"
    s.source       = { :git => "https://github.com/author/RNYamap.git", :tag => "master" }
    s.source_files = "ios/**/*.{h,m,swift}"
    s.preserve_path = "${POD_ROOT}/RNYamap/RNYamap-Bridging-Header.h"
    s.xcconfig = { 'SWIFT_OBJC_BRIDGING_HEADER' => '${PODS_ROOT}/RNYamap/RNYamap-Bridging-Header.h' } 
    # s.requires_arc = true

    s.dependency "React"
    s.dependency "YandexMapKit"
    s.dependency 'YandexMapKitDirections'
    s.dependency "YandexMapKitTransport"
end
