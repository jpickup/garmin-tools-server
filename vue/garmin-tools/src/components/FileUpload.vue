<template>
  <div class="file-upload">
      <h3>{{ description }}</h3>

      <form @submit.prevent="uploadFile" class="space-y-4">
        <input
          type="file"
          @change="handleFileChange"
          class="block w-full text-sm text-gray-700 border border-gray-300 rounded-lg p-2"
        />

        <div v-if="showMaxSchedule" class="flex items-center gap-3">
          <input
            id="maxScheduleEnabled"
            type="checkbox"
            v-model="maxScheduleEnabled"
            class="h-4 w-4 text-blue-600 border-gray-300 rounded"
          />
          <label for="maxScheduleEnabled" class="text-sm text-gray-700">
            Limit number of workouts
          </label>
          <input
            v-if="maxScheduleEnabled"
            type="number"
            v-model.number="maxScheduleValue"
            min="1"
            class="w-24 text-sm text-gray-700 border border-gray-300 rounded-lg p-1"
            placeholder="Max"
          />
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
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const props = defineProps({
  description: {
    type: String,
    default: ''
  },
  url: {
    type: String
  },
  showMaxSchedule: {
    type: Boolean,
    default: false
  }
})

const selectedFile = ref(null)
const message = ref('')
const loading = ref(false)
const maxScheduleEnabled = ref(false)
const maxScheduleValue = ref(25)

function handleFileChange(event) {
  selectedFile.value = event.target.files[0]
}

async function uploadFile() {
  if (!selectedFile.value) return

  loading.value = true
  message.value = 'Uploading file...'

  try {
    const formData = new FormData()
    formData.append('file', selectedFile.value);

    let post_url = import.meta.env.VITE_BACKEND_URI + props.url;
    if (props.showMaxSchedule && maxScheduleEnabled.value && maxScheduleValue.value) {
      post_url += `?maxSchedule=${maxScheduleValue.value}`;
    }
    console.log('Posting to URL:', post_url);

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
.file-upload {

}

</style>
