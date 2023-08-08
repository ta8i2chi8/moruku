import { defineNuxtConfig } from 'nuxt/config';
import vuetify from 'vite-plugin-vuetify';

export default defineNuxtConfig({
  app: {
    head: {
      title: 'モルク',
      meta: [{ name: 'description', content: 'A platform that connects Molkky athletes' }],
      link: [{ rel: 'icon', href: '/icon.jpg' }],
    },
  },
  build: {
    transpile: ['vuetify'],
  },
  hooks: {
    'vite:extendConfig': (config) => {
      config.plugins!.push(vuetify())
    },
  },
  vite: {
    ssr: {
      noExternal: ['vuetify'],
    },
    define: {
      'process.env.DEBUG': false,
    },
  },
  css: ['@/assets/main.scss'],
  runtimeConfig: {
    public: {
      FB_API_KEY: "",
      FB_AUTH_DOMAIN: "",
      FB_PROJECT_ID: "",
      FB_STORAGE_BUCKET: "",
      FB_MESSAGING_SENDER_ID: "",
      FB_APP_ID: "",
      FB_MEASUREMENT_ID: "",
      BASE_URL: "",
    },
  },
});