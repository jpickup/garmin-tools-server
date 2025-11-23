<template>
  <div class="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-8">
    <div class="bg-white shadow-xl rounded-2xl p-6 w-full max-w-md">
      <h1 class="text-2xl font-bold mb-4 text-center">Convert Excel workout to Garmin FIT or iCal</h1>

      <form @submit.prevent="uploadFile" class="space-y-4">
        <input
          type="file"
          @change="handleFileChange"
          class="block w-full text-sm text-gray-700 border border-gray-300 rounded-lg p-2"
        />

        <div>Conversion: {{ conversion }}<br/>
        <input type="radio" id="FIT" name="conversion" value="fit" v-model="conversion">
        <label for="FIT">Garmin FIT</label>
        <input type="radio" id="ICS" name="conversion" value="ics" v-model="conversion">
        <label for="ICS">iCal</label>
        </div>

        <button
          type="submit"
          :disabled="!selectedFile || loading"
          class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 disabled:opacity-50"
        >
          <span v-if="!loading">Upload</span>
          <span v-else>Uploading...</span>
        </button>
      </form>

      <div v-if="message" class="mt-4 text-center text-gray-700">
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const selectedFile = ref(null)
const conversion = ref('')
const message = ref('')
const loading = ref(false)

const API_URL = 'http://localhost:8080/api/schedule/'

function handleFileChange(event) {
  selectedFile.value = event.target.files[0]
}

async function uploadFile() {
  if (!selectedFile.value) return

  loading.value = true
  message.value = 'Uploading file...'

  try {
    const formData = new FormData()
    formData.append('file', selectedFile.value)

    const post_url = API_URL + conversion.value

    const response = await axios.post(post_url, formData, {
      responseType: 'blob', // important for binary data
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })

    console.log(response.headers);
    // Extract filename from response headers (optional)
    const disposition = response.headers['content-disposition']
    let filename = 'downloaded_file'
    console.log(disposition);

    if (disposition && disposition.includes('filename=')) {
      filename = disposition.split('filename=')[1].replace(/["']/g, '')
    }

    // Create a download link
    const blob = new Blob([response.data])
    const url = window.URL.createObjectURL(blob)
    const link = document.createElement('a')
    link.href = url
    link.setAttribute('download', filename)
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
    window.URL.revokeObjectURL(url)

    message.value = 'File converted successfully!'
  } catch (error) {
    console.error(error)
    message.value = 'Error during upload or download.'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', sans-serif;
}
</style>
