import Practice from "@/domain/entitys/practice";

type RequestBody = {
  title: string;
  description: string;
  maxParticipants: number | null;
  prefectureId: number | null;
  heldOn: string | null;
}

export interface PracticeRepository {
  getPractices(limit?: number, offset?: number): Promise<Practice[]>;
  getMyPractices(limit?: number, offset?: number): Promise<Practice[]>;
  getMyJoinedPractices(limit?: number, offset?: number): Promise<Practice[]>;
  searchPractices(
    prefectureId: number | null, 
    from: string | null, 
    to: string | null, 
    keyword: string | null, 
    limit?: number, 
    offset?: number
  ): Promise<Practice[]>;
  getPracticeById(uuid: string): Promise<Practice>;
  insertPractice(requestBody: RequestBody): Promise<void>;
  joinPractice(practiceUuid: string): Promise<void>;
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

  async getMyPractices(limit: number = 20, offset: number = 0): Promise<Practice[]> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi('/me/practices', {
      params: {
        limit: limit,
        offset: offset,
      },
    });
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: getMyPractices API"
      });
    }
    return data.value;
  }

  async getMyJoinedPractices(limit: number = 20, offset: number = 0): Promise<Practice[]> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi('/me/practices/join', {
      params: {
        limit: limit,
        offset: offset,
      },
    });
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: getMyJoinedPractices API"
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
        statusMessage: "error: searchPractices API"
      });
    }
    return data.value;
  }

  async getPracticeById(uuid: string): Promise<Practice> {
    const { data, pending, error, refresh } = await useFetchMorukuPublicApi(`/practices/${uuid}`);
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: getPracticeById API"
      });
    }
    return data.value;
  }

  async insertPractice(requestBody: RequestBody): Promise<void> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi('/practices', {
      method: "POST",
      body: requestBody,
    });
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: insertPractice API"
      });
    }
    return data.value;
  }

  async joinPractice(practiceUuid: string): Promise<void> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi(`/practices/${practiceUuid}/join`, {
      method: "POST",
    });
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: joinPractice API"
      });
    }
    return data.value;
  }

  async cancelPractice(practiceUuid: string): Promise<void> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi(`/practices/${practiceUuid}/join`, {
      method: "DELETE",
    });
    if (error.value !== null) {
      console.error(error.value);
      throw createError({
        statusCode: error.value.statusCode, 
        statusMessage: "error: cancelPractice API"
      });
    }
    return data.value;
  }
}