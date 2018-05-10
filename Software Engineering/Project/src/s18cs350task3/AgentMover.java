package s18cs350task3;

public class AgentMover implements I_Updatable {

    private double x, y, z;
    private double speed, speedTarget, speedDeltaAccelerate, speedDeltaDecelerate, speedMax;
    private double azimuth, azimuthTarget, azimuthDelta;
    private double altitudeDelta, altitudeTarget;

    public AgentMover(double x, double y, double z, double speed, double speedDeltaAccelerate,
                      double speedDeltaDecelerate, double speedMax, double azimuth,
                      double azimuthDelta, double altitudeDelta) {

        if (speed < 0 || speed > speedMax || speedDeltaAccelerate < 0 || speedDeltaDecelerate < 0 ||
                speedMax < 0 || azimuth < 0 || azimuth > 360 || azimuthDelta < 0 ||
                azimuthDelta > 360 || altitudeDelta < 0 || z < 0) {
            throw new RuntimeException("Invalid input");
        }
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed; //speed is distance per tick
        this.speedTarget = speed;
        this.speedDeltaAccelerate = speedDeltaAccelerate;
        this.speedDeltaDecelerate = speedDeltaDecelerate;
        this.speedMax = speedMax;
        this.azimuth = azimuth;
        this.azimuthTarget = azimuth;
        this.azimuthDelta = azimuthDelta;
        this.altitudeDelta = altitudeDelta;
        this.altitudeTarget = z;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSpeedDeltaAccelerate() {
        return speedDeltaAccelerate;
    }

    public double getSpeedDeltaDecelerate() {
        return speedDeltaDecelerate;
    }

    public double getSpeedMax() {
        return speedMax;
    }

    public double getSpeedMin() {
        return 0;
    }

    public double getSpeedTarget() {
        return speedTarget;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public double getAzimuthDelta() {
        return azimuthDelta;
    }

    public double getAzimuthTarget() {
        return azimuthTarget;
    }

    public double getAltitudeDelta() {
        return altitudeDelta;
    }

    public boolean hasAttainedTargetAltitude() {
        return z == altitudeTarget;
    }

    public boolean hasAttainedTargetAzimuth() {
        return azimuth == azimuthTarget;
    }

    public boolean hasAttainedTargetSpeed() {
        return speed == speedTarget;
    }

    private boolean isAzimuthTargetClockwise() {

        double calc = azimuthTarget - azimuth;
        return calc > 0 && calc <= 180 || calc < 0 && (360 + calc) <= 180;

    }

    public boolean isProximate(double x, double y, double z, double distance) {

        if (distance < 0) {
            throw new RuntimeException("Invalid distance");
        }
        double calc = Math.sqrt((x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) +
                (z - this.z) * (z - this.z));
        return calc <= distance;

    }

    public void setAltitudeTarget(double altitude) {

        if (altitude < 0) {
            throw new RuntimeException();
        }
        altitudeTarget = altitude;

    }

    public void setAzimuthTarget(double azimuth, boolean isClockwise) {

        if (azimuth < 0 || azimuth > 360) {
            throw new RuntimeException("Invalid azimuth");
        }
        double calc;
        if (isClockwise) {
            calc = this.azimuth + azimuth;
            this.azimuthTarget = (calc <= 360) ? calc : 360 - calc;
        } else {
            calc = this.azimuth - azimuth;
            this.azimuthTarget = (calc >= 0) ? calc : 360 + calc;
        }

    }

    public void setSpeedTarget(double speed) {

        if (speed < 0 || speed > speedMax) {
            throw new RuntimeException("Invalid speed");
        }
        speedTarget = speed;

    }

    @Override
    public void update_() {

        if (azimuth != azimuthTarget) {

            double calc;
            if (isAzimuthTargetClockwise()) {

                calc = azimuth + azimuthDelta;
                if (calc > 360) {

                    calc = calc - 360;
                    if (azimuthTarget > 180 || azimuthTarget < calc) {
                        calc = azimuthTarget;
                    }

                } else {

                    if (calc > azimuthTarget) {
                        calc = azimuthTarget;
                    }

                }

            } else {

                calc = azimuth - azimuthDelta;
                if (calc < 0) {

                    calc = calc + 360;
                    if (azimuthTarget < 180 || azimuthTarget > calc) {
                        calc = azimuthTarget;
                    }

                } else {

                    if (calc < azimuthTarget) {
                        calc = azimuthTarget;
                    }

                }

            }
            azimuth = calc;

        }//azimuth

        if (speed != speedTarget) {
            speed = updateHelper(speed, speedTarget, speedDeltaAccelerate, speedDeltaDecelerate);
        }//speed

        if (z != altitudeTarget) {
            z = updateHelper(z, altitudeTarget, altitudeDelta, altitudeDelta);
        }//altitude

        double radians = (Math.PI / 180) * (90 - azimuth); //so 0 points "north"
        x = (x + speed * Math.cos(radians));
        y = (y + speed * Math.sin(radians));

    }

    private double updateHelper(double curr, double target, double delta1, double delta2) {

        double calc;
        if (curr < target) {

            calc = curr + delta1;
            if (calc > target) {
                calc = target;
            }

        } else {

            calc = curr - delta2;
            if (calc < target) {
                calc = target;
            }

        }
        return calc;

    }

    @Override
    public String toString() {

        return "(" + x + ", " + y + ", " + z + ")\nSpeed: " + speed +
                "\nAcceleration: " + speedDeltaAccelerate + "\nDeceleration: " +
                speedDeltaDecelerate + "\nMax Speed: " + speedMax +
                "\nAzimuth: " + azimuth + "\nAzimuth Delta: " + azimuthDelta +
                "Altitude Delta: " + altitudeDelta;

    }

}
