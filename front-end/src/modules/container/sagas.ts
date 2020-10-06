import { getContainerListAsync, GET_CONTAINER_LIST } from "./actions";
import { getContainerList } from "../../api/container";
import { takeEvery } from "redux-saga/effects";
import createAsyncSaga from "../../lib/createAsyncSaga";

const getUserProfileSaga = createAsyncSaga(
  getContainerListAsync,
  getContainerList
);

export function* containerSaga() {
  yield takeEvery(GET_CONTAINER_LIST, getUserProfileSaga);
}
