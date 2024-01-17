function showDivSettings() {
    settings.classList.remove("hidden")
}

function showDivExtra() {
    btn_showExtra.hidden = true
    extra.classList.remove("hidden")
}

function uploadVideo() {
    let v = document.createElement("input")
    v.type = "file"
    v.accept = "video/*"
    v.multiple = false
    v.onsub
    v.oninput = () => {
        // spring form handling? DIY POST request?
        // if I want to both limit clients to one job at-a-time and persist in case of a refresh, maybe track sessions...

        //TODO: Return and display ffprobe results
        //populate options with results
        showDivSettings()
    }
    v.click()
}