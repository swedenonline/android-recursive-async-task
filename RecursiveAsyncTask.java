private class playAudio extends AsyncTask<Integer, Integer, Boolean> {
 
        @Override
        protected Boolean doInBackground(Integer... arg0) {
            if (stopAsyncTask) {
                return true;
            }
            Integer counter = arg0[0] + 1;
            publishProgress(counter);
            return false;
        }
 
        @Override
        protected void onProgressUpdate(Integer... arg0) {
            setProgressPercent(arg0[0]);
            super.onProgressUpdate(arg0);
        }
 
        @Override
        protected void onCancelled() {
            resetOnCancel();
            super.onCancelled(true);
        }
 
        @Override
        protected void onPostExecute(Boolean result) {
            if(mCounter >= (millisecs/1000) || result) {
                resetOnCancel();
                cancel(true);
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new playAudio().execute(mCounter);
                    }
                }, mInterval);
            }
            super.onPostExecute(result);
        }//end onPostExecute
    }
