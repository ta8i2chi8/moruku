import Practice from "@/domain/entitys/practice";

export interface PracticeRepository {
  getPractices(): Promise<Practice[]>;
}

export class PracticeRepositoryImpl implements PracticeRepository {
  async getPractices(): Promise<Practice[]> {
    const { data, pending, error, refresh } = await useFetchMorukuPublicApi('/practices');
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: getPractices API"
      });
    }
    return data.value;
  }
}