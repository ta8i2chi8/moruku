import Practice from "@/domain/entitys/practice";

export interface PracticeRepository {
  getPractices(limit?: number, offset?: number): Promise<Practice[]>;
  searchPractices(
    prefectureId: number | null, 
    from: string | null, 
    to: string | null, 
    keyword: string | null, 
    limit?: number, 
    offset?: number
  ): Promise<Practice[]>;
}

export class PracticeRepositoryImpl implements PracticeRepository {
  async getPractices(limit: number = 20, offset: number = 0): Promise<Practice[]> {
    const { data, pending, error, refresh } = await useFetchMorukuPublicApi('/practices', {
      params: {
        limit: limit,
        offset: offset,
      },
    });
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: getPractices API"
      });
    }
    return data.value;
  }

  async searchPractices(    
    prefectureId: number | null, 
    from: string | null, 
    to: string | null, 
    keyword: string | null, 
    limit: number = 20, 
    offset: number = 0
  ): Promise<Practice[]> {
    const { data, pending, error, refresh } = await useFetchMorukuPublicApi('/practices/search', {
      params: {
        prefectureId: prefectureId,
        from: from,
        to: to,
        keyword: keyword,
        limit: limit,
        offset: offset,
      },
    });
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