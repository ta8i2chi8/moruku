import { UseFetchOptions } from "nuxt/app"
import { KeysOf } from "nuxt/dist/app/composables/asyncData"

export const useFetchMorukuPublicApi = async (request: any, opts?: UseFetchOptions<any, any, KeysOf<any>, null, any, "get" | "post" | "put" | "delete"> | undefined) => {
  const config = useRuntimeConfig();

  return await useFetch(request, { 
    baseURL: config.public.BASE_URL, 
    ...opts 
  });
}