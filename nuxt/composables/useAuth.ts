import type { Auth, User } from "firebase/auth";
import {
  getAuth,
  onAuthStateChanged,
  signInWithPopup,
  signOut as firebaseSignOut,
  GoogleAuthProvider,
} from "firebase/auth";
import { computed, ref } from "vue";

export const useAuth = (auth: Auth = getAuth()) => {
  const user = ref<User | null>(auth.currentUser);
  const isAuthed = computed(() => !!user.value); // 古いブラウザに対応するための二重否定

  // idTokenが変化したら更新する
  auth.onIdTokenChanged((authUser) => (user.value = authUser));

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

      // TODO: DBに、googleの名前・メールアドレス、firebase authのuidを登録
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

  // メールアドレスでサインイン
  const signInWithEmail = async () => {
    
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
    checkAuthState, 
    signUpWithGoogle, 
    signInWithGoogle, 
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