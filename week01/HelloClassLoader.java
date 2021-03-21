import java.io.*;

public class HelloClassLoader extends ClassLoader{
    private String path = "D:/ideaspace/study/week01/src/";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        new HelloClassLoader().findClass("Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {
        byte[] data = null;
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(new File(path + File.separator + name + ".xlass"));
            int len = 0;
            while (-1 != (len = in.read())){
                out.write(len);
            }
            data = out.toByteArray();
            for(int i=0; i<data.length; i++){
                int a = 255 - data[i];
                data[i] = (byte) a;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
