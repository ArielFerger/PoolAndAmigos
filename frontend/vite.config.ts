import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    // Escucha en 0.0.0.0: sin esto el servidor solo acepta conexiones
    // desde dentro del contenedor y no se ve desde Windows.
    host: true,
    port: 5173,
    watch: {
      // Los eventos de archivo no cruzan los bind mounts de Windows,
      // asi que el hot reload necesita polling.
      usePolling: true,
    },
  },
})
