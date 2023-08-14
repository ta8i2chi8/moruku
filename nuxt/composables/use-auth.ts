import type { Auth, User } from "firebase/auth";
import {
  getAuth,
  onAuthStateChanged,
  signInWithPopup,
  createUserWithEmailAndPassword,
  sendEmailVerification,
  signInWithEmailAndPassword,
  signOut as firebaseSignOut,
  GoogleAuthProvider,
} from "firebase/auth";
import { computed, ref } from "vue";
import { UserRepositoryImpl } from '@/domain/repositories/user-repository';

export const useAuth = (auth: Auth = getAuth()) => {
  const userRepository = new UserRepositoryImpl();

  const user = ref<User | null>(auth.currentUser);
  const isAuthed = computed(() => !!user.value && user.value.emailVerified); // 古いブラウザに対応するための二重否定

  // idTokenが変化したら更新する
  auth.onIdTokenChanged((authUser) => (user.value = authUser));

  // userの情報を更新
  const reloadCurrentUser = async () => {
    await user.value?.reload();
  };

  // 認証状態チェック
  const checkAuthState = async () => {
    try {
      const _user = await _checkAuthState(auth);
      user.value = _user;
    } catch (error) {
      user.value = null;
    }
  };

  // Googleでサインアップ
  const signUpWithGoogle = async () => {
    try {
      const provider = new GoogleAuthProvider();
      await signInWithPopup(auth, provider);

      // DBに、googleの名前・メールアドレス、firebase authのuidを登録
      await userRepository.insertUser({
        nickname: user.value?.displayName || '',
        email: user.value?.email || '',
        firebaseUid: user.value?.uid || '',
      });
    } catch (error) {
      throw error;
    }
  };
  
  // Googleでサインイン
  const signInWithGoogle = async () => {
    try {
      const provider = new GoogleAuthProvider();
      await signInWithPopup(auth, provider);
    } catch (error) {
      throw error;
    }
  };

  // メールアドレスでサインアップ
  const signUpWithEmail = async (nickname: string, email: string, password: string) => {
    try {
      await createUserWithEmailAndPassword(auth, email, password);

      // DBに、ニックネーム・メールアドレス、firebase authのuidを登録
      await userRepository.insertUser({
        nickname: nickname,
        email: email,
        firebaseUid: user.value?.uid || '',
      });
    } catch (error) {
      throw error;
    }
  };

  // メールアドレス確認メールを送信
  const verifyEmail = async () => {
    try {
      if (user.value) {
        await sendEmailVerification(user.value);
      }
    } catch (error) {
      throw error;
    }
  }

  // メールアドレスでサインイン
  const signInWithEmail = async (email: string, password: string) => {
    try {
      await signInWithEmailAndPassword(auth, email, password);
    } catch (error) {
      throw error;
    }
  };

  // サインアウト
  const signOut = async () => {
    try {
      await firebaseSignOut(auth);
      user.value = null;
    } catch (error) {
      throw error;
    }
  };

  return { 
    user, 
    isAuthed,
    reloadCurrentUser,
    checkAuthState, 
    signUpWithGoogle,
    signInWithGoogle,
    signUpWithEmail,
    verifyEmail,
    signInWithEmail,
    signOut 
  };
};

// onAuthStateChangedのPromise版Util
const _checkAuthState = async (auth: Auth) => {
  return new Promise<User | null>((resolve, reject) => {
    // client only
    if (process.server) return resolve(null);

    onAuthStateChanged(
      auth,
      (user) => resolve(user || null),
      (error) => reject(error)
    );
  });
};