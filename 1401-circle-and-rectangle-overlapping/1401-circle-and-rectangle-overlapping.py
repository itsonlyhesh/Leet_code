class Solution(object):

  def checkOverlap(self, radius, xCenter, yCenter, x1, y1, x2, y2):
    """
    :type radius: int
    :type xCenter: int
    :type yCenter: int
    :type x1: int
    :type y1: int
    :type x2: int
    :type y2: int
    :rtype: bool
    """
    # Clamp circle center to the rectangle's boundary to find the closest point
    closest_x = max(x1, min(xCenter, x2))
    closest_y = max(y1, min(yCenter, y2))

    # Compute squared distance to avoid floating-point square root operations
    dx = closest_x - xCenter
    dy = closest_y - yCenter

    return (dx * dx + dy * dy) <= (radius * radius)