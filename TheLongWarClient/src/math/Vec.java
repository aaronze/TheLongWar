package math;

/**
 * @author Aaron
 */
public class Vec {
    public double x = 0;
    public double y = 0;
    
    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec() {
        
    }
    
    public void m_Set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public void m_Set(Vec v) {
        x = v.x;
        y = v.y;
    }
    
    public Vec set(Vec v) {
        x = v.x;
        y = v.y;
        return this;
    }
    
    public Vec add(Vec v) {
        return new Vec(x + v.x, y + v.y);
    }
    
    public void m_Add(Vec v) {
        x += v.x;
        y += v.y;
    }
    
    public Vec subtract(Vec v) {
        return new Vec(x - v.x, y - v.y);
    }
    
    public void m_Subtract(Vec v) {
        x -= v.x;
        y -= v.y;
    }
    
    public Vec scale(double scale) {
        x *= scale;
        y *= scale;
        return this;
    }
    
    public void m_Scale(double scale) {
        x *= scale;
        y *= scale;
    }
    
    public void m_Normalize() {
        double len = Math.sqrt(x*x + y*y);
        x /= len;
        y /= len;
    }
    
    public Vec normalize() {
        double len = Math.sqrt(x*x + y*y);
        return new Vec(x/len, y/len);
    }
    
    public Vec rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        
        double nX = x * cos - y * sin;
        double nY = x * sin + y * cos;
        
        return new Vec(nX, nY);
    }
    
    public void m_Rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        
        double nX = x * cos - y * sin;
        double nY = x * sin + y * cos;
        
        x = nX;
        y = nY;
    }
}
