import User from "@/domain/entitys/user";

type RequestBody = {
  nickname: string;
  email: string;
  firebaseUid: string;
}

export interface UserRepository {
  getUserById(uuid: string): Promise<User>;
  getMe(): Promise<User>;
  insertUser(requestBody: RequestBody): Promise<void>;
}

export class UserRepositoryImpl implements UserRepository {
  async getUserById(uuid: string): Promise<User> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi(`/users/${uuid}`);
    if (error.value !== null) {
      console.error(error.value);
      throw error.value;
    }
    return data.value;
  }

  async getMe(): Promise<User> {
    const { data, pending, error, refresh } = await useFetchMorukuPrivateApi('/users/me');
    if (error.value !== null) {
      console.error(error.value);
      throw error.value;
    }
    return data.value;
  }

  async insertUser(requestBody: RequestBody): Promise<void> {
    const { error } = await useFetchMorukuPrivateApi("/users", {
      method: "POST",
      body: requestBody,
    });
    if (error.value !== null) {
      console.error(error.value);
      throw error.value;
    }
  }
}